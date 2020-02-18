package com.videos.Utils;

import java.io.*;
import java.util.List;

/**
 * 
 * @Description: 获取视频的信息
 */
public class FetchVideoCover {
	// 视频路径
	private String ffmpegEXE;

	public void getCover(String videoInputPath, String coverOutputPath) throws IOException, InterruptedException {
//		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
		List<String> command = new java.util.ArrayList<String>();
		command.add(ffmpegEXE);
		
		// 指定截取第1秒
		command.add("-ss");
		command.add("00:00:01");
				
		command.add("-y");
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-vframes");
		command.add("1");
		
		command.add(coverOutputPath);
		
//		for (String c : command) {
//			System.out.print(c + " ");
//		}
		
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

	public String getFfmpegEXE() {
		return ffmpegEXE;
	}

	public void setFfmpegEXE(String ffmpegEXE) {
		this.ffmpegEXE = ffmpegEXE;
	}

	public FetchVideoCover() {
		super();
	}

	public FetchVideoCover(String ffmpegEXE) {
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// 获取视频信息。
//		FetchVideoCover videoInfo = new FetchVideoCover("c:\\ffmpeg\\bin\\ffmpeg.exe");
//		try {
//			videoInfo.getCover("c:\\北京北京.avi","c:\\北京.jpg");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String[] videoPath = getFileName();
		FetchVideoCover videoInfo = new FetchVideoCover("C:\\ffmpeg\\bin\\ffmpeg.exe");
		for(int i=0; i<videoPath.length; i++){
			String path = videoPath[i].split("\\.")[0];
			videoInfo.getCover("C:/video/"+videoPath[i],"C:/video/" + path +".jpg");
		}

	}

	public static String[] getFileName(){
		String videoPath[] = new String[45];
		String path = "C:/video";
		File f= new File(path);
		if(!f.exists()){
			return null;
		}
		File file[] = f.listFiles();
		for(int i=0; i<file.length; i++){
			File fs = file[i];
			if(fs.isDirectory()){
				System.out.println(fs.getName() + "目录");
			}else{
				videoPath[i] = fs.getName();
			}
		}
		return videoPath;
	}
}