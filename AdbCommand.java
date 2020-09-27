import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdbCommand {

	private String adbDirectory;

	AdbCommand(String adbDir) {
		adbDirectory = adbDir;
	}

	public void runCommand(String adbCommand) {

		File adbFile = new File(adbDirectory + "/adb");
		if (!adbFile.exists())
			throw new java.lang.Error("adb file not found in the specified directory");

		try {

			// -- Linux --

			// Run a shell command
			Process process = Runtime.getRuntime().exec(adbDirectory + "/" + adbCommand);


			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println(output);
				System.out.println("Shell/adb command executed");
			} else {
				throw new java.lang.Error("Error executing shell/adb command");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
