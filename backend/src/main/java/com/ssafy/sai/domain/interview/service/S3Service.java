package com.ssafy.sai.domain.interview.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.dto.request.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.dto.request.DeleteInterviewVideoRequest;
import com.ssafy.sai.domain.interview.exception.InterviewException;
import com.ssafy.sai.domain.interview.exception.InterviewExceptionType;
import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final MemberRepository memberRepository;
    private final InterviewVideoRepository savedInterviewVideoRepository;
    private final GcsService gcsService;

    /**
     * @????????? S3??? ?????? ??????????????? ?????????
     * @param id ?????????pk
     * @param request ??????id, ??????????????? ??????, ????????????id, ????????????url ??????(openvidu server ???), ????????????
     * @param saveInterviewInfo ?????? ?????? ?????????
     * @throws RuntimeException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Async
    public void uploadFileS3(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo) throws RuntimeException, IOException, ExecutionException, InterruptedException {

//        1. ???????????? ?????? url??? File??? ?????????
//        2. File??? MultipartFile??? ????????? ???????????? ??????
//        3. ????????? ????????? s3??? ?????????
//        4. // s3url, s3name db??? ???????
//        5. ?????? MultipartFile??? File .flac?????? ??????
//        6. File .flac -> MultipartFile .flac?????? ??????
//        7. GCS??? ??????
//        8. stt


        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // openvidu server url?????? ????????? .mp4????????? multipartFile??? ????????? ?????? ???????????? List
        List<MultipartFile> openviduVideoMultipartFileList = new ArrayList<>();
        // openvidu server ?????? ?????????????????? ?????? url
        URL url;

        //s3??? ????????? ?????? ?????? ???????????? List
        List<String> S3videoNameList = new ArrayList<>();
        //s3??? ????????? ?????? url ???????????? List
        List<String> S3videoUrlList = new ArrayList<>();

        // .flac ????????? multipartfile ????????? ???????????? List
        List<MultipartFile> audioMultipartFiles = new ArrayList<>();

        List<String> openviduVideoNames = new ArrayList<>();
        List<String> flacAudioNames = new ArrayList<>();

        // interviewVideoUrl??? ???????????? file ???????????? mp4 ?????????
        for (String openviduVideoUrl : request.getInterviewVideoUrl()) {

            String[] openviduVideoNameSplit = openviduVideoUrl.split("/");
            String openviduVideoName = openviduVideoNameSplit[6];
            openviduVideoNames.add(openviduVideoName);

            // ?????? ??????
            File openviduVideoFile = new File(openviduVideoName);

            url = new URL(openviduVideoUrl);

            // url??? ???????????? ?????? request ?????????
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(url.toString());
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.11 Safari/537.36");
            httpGet.addHeader("Referer", "https://www.google.com");

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("OPENVIDUAPP", "MY_SECRET");
            credsProvider.setCredentials(AuthScope.ANY, credentials);

            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credsProvider);

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, context);
            HttpEntity imageEntity = httpResponse.getEntity();

            if (imageEntity != null) {
                FileUtils.copyInputStreamToFile(imageEntity.getContent(), openviduVideoFile);
            }

            httpGet.releaseConnection();

            // file -> MultipartFile ??????
            DiskFileItem fileItem = null;
            fileItem = new DiskFileItem("file", Files.probeContentType(openviduVideoFile.toPath()), false, openviduVideoFile.getName(), (int) openviduVideoFile.length(), openviduVideoFile.getParentFile());

            InputStream input = new FileInputStream(openviduVideoFile);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);

            MultipartFile openviduVideoMultipartFile = new CommonsMultipartFile(fileItem);


            openviduVideoMultipartFileList.add(openviduVideoMultipartFile);
            // ????????? ???
        }

        // ?????? ?????? MultipartFileList??? ????????? ?????? s3??? ????????? ??? flac ?????? ?????????
        openviduVideoMultipartFileList.forEach(openviduVideoFile -> {

            // s3??? ?????????
            // ?????? s3??? ???????????? ?????? ??????
            String videoName = createFileName(openviduVideoFile.getOriginalFilename());
            String videoUrl;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(openviduVideoFile.getSize());
            objectMetadata.setContentType(openviduVideoFile.getContentType());

            try (InputStream inputStream = openviduVideoFile.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, videoName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                videoUrl = amazonS3.getUrl(bucket, videoName).toString();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "?????? ???????????? ??????????????????.");
            }
            S3videoNameList.add(videoName);
            S3videoUrlList.add(videoUrl);


            int pointIndex = videoName.indexOf(".");
            String audioName = videoName.substring(0, pointIndex);

            Encoder encoder = new Encoder();

            File source = new File(openviduVideoFile.getOriginalFilename());

            try {
                source.createNewFile();
                FileOutputStream fos = new FileOutputStream(source);
                fos.write(openviduVideoFile.getBytes());
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            File target = new File(audioName + ".flac");
            flacAudioNames.add(audioName + ".flac");

            // ????????? ?????? ?????? ??????. Google speech to text api ?????? ??????
            AudioAttributes audio = new AudioAttributes();
            audio.setSamplingRate(16000); // ?????? ?????????
            audio.setChannels(1); // Mono ????????? ???????????? speech to text api ?????? ??????
            audio.setCodec("flac");  // ?????? ??????

            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("flac"); // ?????? ??????
            attrs.setAudioAttributes(audio);

            try {
                encoder.encode(source, target, attrs);
            } catch (EncoderException e) {
                throw new RuntimeException(e);
            }

            ////////////////////////////
            // file -> MultipartFile ??????
            DiskFileItem fileItem = null;
            try {
                fileItem = new DiskFileItem("file", Files.probeContentType(target.toPath()), false, target.getName(), (int) target.length(), target.getParentFile());

                InputStream input = new FileInputStream(target);
                OutputStream os = fileItem.getOutputStream();
                IOUtils.copy(input, os);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MultipartFile audioMultipartFile = new CommonsMultipartFile(fileItem);
            audioMultipartFiles.add(audioMultipartFile);
            ///////////////////////////////

            if (source.exists()) { //????????????????????????
                source.delete();
            }

        });


        //gcs??? ?????????
        gcsService.uploadFileGcs(id, request, saveInterviewInfo, audioMultipartFiles, openviduVideoNames, flacAudioNames, S3videoUrlList, S3videoNameList);


    }

    public void deleteFileS3(List<InterviewVideo> findInterviewVideos) throws MemberException {
        // ?????? ????????? ?????? -> ????????????
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        for (InterviewVideo findInterviewVideo : findInterviewVideos) {
            System.out.println("findInterviewVideo.getVideoName() = " + findInterviewVideo.getVideoName());
            // ??????, s3??? ????????? ??????????????? ????????????.
            if (!amazonS3.doesObjectExist(bucket, findInterviewVideo.getVideoName())) {
                // ???????????? ???????????? ?????? ???????????????
                throw new InterviewException(InterviewExceptionType.NOT_FOUND_S3_VIDEO);
            }

            // S3?????? ?????? ?????? ??????
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, findInterviewVideo.getVideoName()));

            // s3?????? ?????? ??? ??????????????? ?????? ?????? ??????
            if (amazonS3.doesObjectExist(bucket, findInterviewVideo.getVideoName())) {
                // ??????????????? ?????? ???????????????
                throw new InterviewException(InterviewExceptionType.STILL_EXIST_S3_VIDEO);
            }

        }

        // ??? ?????????????????? ?????? ?????? ??????
        // DB??? ?????? ?????? ????????? ????????? - ????????? ?????? ?????? ??????!
        // ???????????? PK??? ??????????????? ??????????????????

        // ?????? ??? ????????? ???????????? ????????? ??? ?????? ??? ????????? ?????? ???????????? ????????? void

    }

    /**
     * @????????? ?????? ?????? ???????????? ???????????? ??????
     * @param fileName ?????? ??????
     * @return
     */
    private String createFileName(String fileName) { // ?????? ?????? ????????? ???, ???????????? ??????????????? ?????? random?????? ????????????.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * @????????? file ????????? ????????? ????????? ???????????? ?????? ?????? (?????? ????????? ???????????? ???????????? ??? ?????? ?????? ?????? .??? ?????? ????????? ??????)
     * @param fileName ?????? ??????
     * @return
     */
    private String getFileExtension(String fileName) { // file ????????? ????????? ????????? ???????????? ?????? ???????????? ????????????, ?????? ????????? ???????????? ???????????? ??? ?????? ?????? ?????? .??? ?????? ????????? ?????????????????????.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "????????? ????????? ??????(" + fileName + ") ?????????.");
        }
    }


}
