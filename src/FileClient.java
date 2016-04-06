import java.io.*;
import java.rmi.*;
import java.util.Scanner;

public class FileClient {
	
	public static void main(String args[]) {
		try {
			String name = "//127.0.0.1/RMI";
			FileInterface fi = (FileInterface) Naming.lookup(name);
			ShowHelp();
			String CurrentDirectory = "d:";
			String[] fileNameStrings = null;
			Scanner scanner = new Scanner(System.in);
			while (true) {
				try{
					System.out.print(CurrentDirectory + ":$");
					String orderString = scanner.next();
					if (orderString.equals("dir")) {
						fileNameStrings = fi.ListFile(CurrentDirectory);
						for (int i = 0; i < fileNameStrings.length; i++) {
							System.out
									.println("[" + i + "]\t" + fileNameStrings[i]);
						}
					} else if (orderString.equals("cd")) {
						int DirIndex = scanner.nextInt();
						CurrentDirectory = fileNameStrings[DirIndex];
					} else if (orderString.equals("download")) {
						int FileIndex = scanner.nextInt();
						String NewFileName = scanner.next();
						FileOutputStream output = new FileOutputStream(NewFileName);
						int startpos = 0;
						while (true){
							byte[] fileData = fi.DownloadFile(fileNameStrings[FileIndex],startpos);
							if (fileData == null){
								break;
							}
							output.write(fileData);
							startpos += fileData.length;
						}
						output.close();
					} else if (orderString.equals("help")) {
						ShowHelp();
					} else if (orderString.equals("exit")) {
						break;
					}
				} catch (Exception e){
					System.err.println(e.getMessage());
					e.printStackTrace();
					continue;
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	static void ShowHelp(){
		System.out.println("Now you can browse the file in server.");
		System.out
				.println("input \"dir\" to list the files in current directory.");
		System.out
				.println("input \"cd [directoryindex]\" to change current directory.");
		System.out
				.println("input \"download [fileindex] [newfilename]\" to download file to current console directory.");
		System.out.println("input \"help\" to show this message.");
		System.out.println("input \"exit\" to exit client.");
	}

}
