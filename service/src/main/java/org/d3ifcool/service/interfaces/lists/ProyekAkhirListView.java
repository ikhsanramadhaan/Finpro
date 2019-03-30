package org.d3ifcool.service.interfaces.lists;

import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.List;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface ProyekAkhirListView {


    void showProgress();

    void hideProgress();

    void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList);

    void onFailed(String message);

}
