package com.videos.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public void convertor(String videoInputPath, String mp3InputPath, String videoOutputPath) throws Exception {
//		ffmpeg -i video.mp4 -i audio.wav -c:v copy -c:a aac -strict experimental
//-map 0:v:0 -map 1:a:0 output.mp4

		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-i");
		command.add(mp3InputPath);
		
		command.add("-c:v");
		command.add("copy");
		command.add("-c:a");
		command.add("aac");
		command.add("-strict");
		command.add("experimental");
		command.add("-map");
		command.add("0:v:0");
		command.add("-map");
		command.add("1:a:0");
		
//		command.add("-y");
		command.add(videoOutputPath);
		
//		for (String c : command) {
//			System.out.print(c + " ");
//		}
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
//
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

	public static void main(String[] args) {
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertor("C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/wxb0c9998dbc9c3896.o6zAJs5zHv8BGR1Yg5sfaHDT6x50.ZqcW1C7nGlcS0ff2f7b33927b4f9cce15d77a55debf0.mp4", "C:/guoxicheng_videos_dev/bgm/邓壬鑫-告白之夜.mp3", "C:/guoxicheng_videos_dev/oaVkc5D5a22Ydk_jY_KheX1W-xkM/video/d554c78a-4274-403f-8464-f951bcb28fff.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
