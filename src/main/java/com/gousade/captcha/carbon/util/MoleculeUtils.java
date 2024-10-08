package com.gousade.captcha.carbon.util;

import cn.hutool.core.io.file.LineSeparator;
import com.gousade.captcha.carbon.MdlMolParser;
import com.gousade.captcha.carbon.Molecule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2022/12/03
 */
@Slf4j
public class MoleculeUtils {
	private static final List<Molecule> molecules = init();

	private MoleculeUtils() {
	}

	private static List<Molecule> init() {
		List<Molecule> result = new ArrayList<>();
		String locationPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ String.join(File.separator, "static", "captcha", "carbon", "mol", "*.mol");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(locationPattern);
			Arrays.stream(resources).forEach(resource -> {
				StringBuilder molStr = new StringBuilder();
				try (InputStreamReader input = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
				     BufferedReader bufferReader = new BufferedReader(input)) {
					String line;
					while ((line = bufferReader.readLine()) != null) {
						molStr.append(line).append(LineSeparator.LINUX.getValue());
					}
					Molecule molecule = MdlMolParser.parseString(molStr.toString());
					result.add(molecule);
				} catch (IOException | MdlMolParser.BadMolFormatException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		stopWatch.stop();
		log.info("初始化碳分子库用时{}毫秒,共{}个分子文件。", stopWatch.getTotalTimeMillis(), result.size());
		return result;
	}

	public static MoleculeUtils getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public Molecule randomMolecule() {
		return molecules.get(new Random().nextInt(molecules.size()));
	}

	private static class InstanceHolder {
		private static final MoleculeUtils INSTANCE = new MoleculeUtils();
	}
}
