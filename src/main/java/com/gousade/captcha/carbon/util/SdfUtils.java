package com.gousade.captcha.carbon.util;

import com.gousade.util.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * https://pubchemdocs.ncbi.nlm.nih.gov/downloads
 * https://ftp.ncbi.nlm.nih.gov/pubchem/Compound/CURRENT-Full/SDF/
 * SDF(Structural Data File)是大文本文件，里面包含了多个mol的结构定义
 */
public class SdfUtils {
	private SdfUtils() {
		throw new IllegalStateException("no instance");
	}

	/**
	 * Check whether the given file is a Blocked GNU Zip Format file.
	 *
	 * @param file the file to check
	 * @return true if the file is a Blocked GNU Zip Format file
	 * @throws IOException if the file could not be read, e.g. because it does not exist
	 */
	public static boolean isBgzFile(@NotNull File file) throws IOException {
		try (InputStream is = new FileInputStream(file)) {
			byte[] buf = new byte[4];
			IOUtils.read(is, buf, 0, 4);
			return buf[0] == (byte) 0x1F && buf[1] == (byte) 0x8B && buf[2] == (byte) 0x8 && buf[3] == (byte) 0x4;
		}
	}

	public static void splitSDFFile(String inputPath, String outputDir) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
		String line = reader.readLine();
		List<String> list = new ArrayList<>();
		while (line != null) {
			if (!ObjectUtils.isEmpty(line)) {
				list.add(line);
			}
			//$$$$代表一个分子结束
			if (Objects.equals(line.trim(), "$$$$")) {
				FileUtils.write2File(list, outputDir, list.get(0) + ".mol");
				list.clear();
			}
			line = reader.readLine();
		}
		reader.close();
	}
}
