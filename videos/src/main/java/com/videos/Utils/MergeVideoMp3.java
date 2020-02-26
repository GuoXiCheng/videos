package com.videos.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}

	public void removeBgm(String videoInputPath, String videoOutputPath) throws IOException {
		//		ffmpeg  -i ./plutopr.mp4 -vcodec copy -acodec copy -ss 00:00:10 -to 00:00:15 ./cutout1.mp4 -y
		List<String> commandCut = new ArrayList<>();
		commandCut.add(ffmpegEXE);

		commandCut.add("-i");
		commandCut.add(videoInputPath);

		commandCut.add("-vcodec");
		commandCut.add("copy");
		commandCut.add("-an");
		commandCut.add(videoOutputPath);

		ProcessBuilder builderCut = new ProcessBuilder(commandCut);
		Process processCut = builderCut.start();

        InputStream errorStream = processCut.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
	}

	public void convertor(String videoInputPath, String mp3InputPath, String videoSeconds,String videoOutputPath) throws Exception {
//		ffmpeg -i video.mp4 -i audio.wav -c:v copy -c:a aac -strict experimental
//-map 0:v:0 -map 1:a:0 output.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		command.add("-i");
		command.add(videoInputPath);

		command.add("-i");
		command.add(mp3InputPath);

		command.add("-t");
		command.add(videoSeconds);

		command.add(videoOutputPath);

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
		
	}

	public void merge(String videoInputPath, String mp3InputPath, String videoSeconds,String videoOutputPath) throws Exception {
		String id = KeyUtil.genUniqueKey();
		String tempPath = "C:/guoxicheng_videos_dev/temp/" + id + ".mp4";
		removeBgm(videoInputPath,tempPath);
		File file = new File(tempPath);
        while (!file.exists()){
            System.out.println(new Date().getTime());
        }
		convertor(tempPath,mp3InputPath,videoSeconds,videoOutputPath);
	}

	public static void main(String[] args) {
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
//		try {
//			ffmpeg.removeBgm("C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/a.mp4", "C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/22.mp4");
//			File file = new File("C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/22.mp4");
//			while(!file.exists()){
//				System.out.println("hhhhhh");
//			}
//			ffmpeg.convertor("C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/22.mp4","C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/b.mp3","10","C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/23.mp4");
//			file.delete();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			ffmpeg.merge("C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/wxb0c9998dbc9c3896.o6zAJs5zHv8BGR1Yg5sfaHDT6x50.BY1qq8ObSUuucfdb09a672c2477142a3ae6159a57373.mp4","C:/guoxicheng_videos_dev/bgm/邓壬鑫-告白之夜.mp3","10","C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/23.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
