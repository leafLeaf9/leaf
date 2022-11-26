package com.gousade.captcha;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.LineSeparator;
import com.gousade.captcha.util.SdfUtils;
import com.gousade.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2022/11/26
 */
public class SdfUtilsTests {
	/**
	 * 解析SDF文件获取多个.mol
	 */
	@Test
	public void splitSDFFile() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("static" + File.separator + "captcha"
				+ File.separator + "carbon" + File.separator + "1W+个碳分子.txt");
		String outputDir = FileUtils.USER_DIR_DIRECTORY + File.separator + "captcha" + File.separator + "carbon"
				+ File.separator;
		SdfUtils.splitSDFFile(classPathResource.getFile().getAbsolutePath(), outputDir);
	}

	/**
	 * 解析到的多个mol文件并不全是包含手性碳的，进行过滤
	 */
	@Test
	public void filterChiralFiles() {
		String molDir = FileUtils.USER_DIR_DIRECTORY + File.separator + "captcha" + File.separator + "carbon"
				+ File.separator;
		String outputDir = molDir + "chiral" + File.separator;
		File directory = new File(molDir);
		File[] files = directory.listFiles();
		Arrays.stream(Objects.requireNonNull(files)).forEach(file -> {
			try {
				Path path = file.toPath();
				String collect = Files.lines(path, Charset.defaultCharset())
						.flatMap(line -> Arrays.stream(line.split(LineSeparator.LINUX.getValue())))
						.collect(Collectors.joining(LineSeparator.LINUX.getValue()));
				Molecule molecule = MdlMolParser.parseString(collect);
//				ChiralCarbonHelper.isChiralCarbon(molecule)
				FileUtil.copy(path, Paths.get(outputDir + File.separator + file.getName()));
			} catch (IOException | MdlMolParser.BadMolFormatException e) {
				e.printStackTrace();
			}
		});
	}
}
