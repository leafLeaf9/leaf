package com.gousade.service.impl;

import com.gousade.captcha.carbon.Molecule;
import com.gousade.captcha.carbon.MoleculeRender;
import com.gousade.captcha.carbon.util.ChiralCarbonHelper;
import com.gousade.captcha.carbon.util.MoleculeUtils;
import com.gousade.entity.dto.ChiralCarbonCaptchaDTO;
import com.gousade.entity.query.ChiralCarbonCaptchaQuery;
import com.gousade.service.ChiralCarbonCaptchaService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2022/12/03
 */
@Service
public class ChiralCarbonCaptchaServiceImpl implements ChiralCarbonCaptchaService {
	@Override
	public ChiralCarbonCaptchaDTO getChiralCarbonCaptcha(ChiralCarbonCaptchaQuery query) {
		Molecule molecule = MoleculeUtils.getInstance().randomMolecule();
		val cfg = initMoleculeConfig(molecule);
		val chirals = ChiralCarbonHelper.getMoleculeChiralCarbons(molecule);
		List<Integer> regions = chirals.stream().map(index -> {
			val gridWidth = cfg.width / cfg.gridCountX;
			val gridHeight = cfg.height / cfg.gridCountY;
			val x = cfg.transformX(molecule, molecule.atomX(index));
			val y = cfg.transformY(molecule, molecule.atomY(index));
			val xn = Math.round(x / gridWidth);
			val yn = Math.round(y / gridHeight);
			return (xn << 4) | yn;
		}).sorted().collect(Collectors.toList());
		cfg.setShownChiralCarbons(query.isHint() ? new ArrayList<>(chirals) : Collections.emptyList());
		return null;
	}

	private MoleculeRender.MoleculeRenderConfig initMoleculeConfig(Molecule molecule) {
		val cfg = MoleculeRender.calculateRenderRect(molecule, 720);
		cfg.gridCountX = 5;
		cfg.gridCountY = 3;
		cfg.drawGrid = true;
		return cfg;
	}
}
