package com.example.kimhao.first_project.Maps;


import com.example.kimhao.first_project.Maps.model.MyLocation;

import java.util.ArrayList;

/*
 * Created by iblinfotech on 04/03/17.
 */


public class GlobalUtils {
    private static ArrayList<MyLocation> latLngsArrayList = new ArrayList<>();

    public static ArrayList<MyLocation> getLatLongArray() {

        addLocations(16.075658, 108.233709);
        addLocations(15.975150, 108.253537);
        addLocations(16.076173, 108.235533);
        addLocations(16.101059, -108.3163313);
        addLocations(16.0984372, -108.235533);
        addLocations(16.1598974, -108.235533);
        addLocations(16.1012814, -108.235533);
        addLocations(16.1490401, -108.235533);
        addLocations(4.1015875, -108.235533);
        addLocations(16.1015875, -108.235533);
        addLocations(16.029298, -108.235533);
        addLocations(16.1582665, 108.235533);
        addLocations(16.04776330000001, 108.235533);
        addLocations(33.9451969, 108.235533);
        addLocations(16.15412490000001, 108.235533);
        addLocations(16.02128249999999, 108.235533);
        addLocations(16.1314077, 108.235533);
        addLocations(16.0875285,108.235533);
        addLocations(16.095153, 108.235533);
        addLocations(16.14839259999999, 108.235533);
        addLocations(33.9613703, 108.235533);
        addLocations(16.0907297, -108.23553);
        addLocations(16.01707689999999, -108.2886818);
        addLocations(16.0708996, -108.40338020000002);
        addLocations(16.0424905, -108.3641055);
        addLocations(16.0721585, -108.40604589999998);
        addLocations(16.0978922, -108.2474823);
        addLocations(16.0875221, -108.29144969999999);
        addLocations(16.1612415, -108.3100729);
        addLocations(16.0642806, -108.37025940000001);
        addLocations(16.1002972, -108.3271408);
        addLocations(16.09316029999999, -108.37831669999998);
        addLocations(16.0515954, -108.38369210000002);
        addLocations(16.0917299, -108.3236639);
        addLocations(16.01707689999999, -108.2886818);
        addLocations(16.08299360000001, -108.24611379999999);
        addLocations(16.1314077, -108.35717339999997);
        addLocations(16.1279922, -108.1559945);
        addLocations(16.0724615, -108.35809949999998);
        addLocations(16.0724304, -108.29071620000002);
        addLocations(16.1582665, -108.2518697);
        addLocations(16.0689235, -108.16681560000001);
        addLocations(33.9800122, -16.0505437);
        addLocations(16.02128249999999, -108.383893);
        addLocations(16.0907297, -108.32014520000001);
        addLocations(16.10248350000001, -108.33849270000002);
        addLocations(16.0606142, -108.3198308);
        addLocations(16.02128249999999, -108.383893);
        addLocations(16.0666516, -108.40100180000002);
        addLocations(16.0515954, -108.3836921);
        addLocations(16.0642254, -108.3701274);
        addLocations(16.1487314, -108.3386863);
        addLocations(16.0447628, -108.26542890000002);
        addLocations(16.0485099, -108.2532776);
        addLocations(16.0563289, -108.24677129999998);
        addLocations(16.073873, -108.24077740000001);
        addLocations(16.1490401, -108.2840485);
        addLocations(16.0724615, -108.35809949999998);
        addLocations(16.0485099, -108.25327759999999);
        addLocations(16.0638208, -108.35546269999998);
        addLocations(16.0724615, -108.35809949999998);
        addLocations(16.0951536, -108.12763719999998);
        addLocations(16.1016325, -108.32694479999998);
        addLocations(16.01775, -108.16394800000001);
        addLocations(16.0997032, -108.3380494);
        addLocations(16.0485099, -108.25327759999999);
        addLocations(16.1013684, -108.33669350000002);
        addLocations(33.97802970000001, -108.39154810000002);
        addLocations(16.1010998, -108.33836450000001);
        addLocations(16.06177009999999, -108.38376970000002);
        addLocations(16.1520338, -108.35111160000003);
        addLocations(16.0538577, -8.403);
        addLocations(16.07, -108.16326529999998);
        addLocations(16.1490401, -108.2840485);
        addLocations(16.1083754, -108.30035429999998);
        addLocations(16.0599922, -108.42000949999999);
        addLocations(16.14870620000001, -108.28146600000002);
        addLocations(16.0402652, -108.26954390000003);
        addLocations(16.0664768, -108.3802384);
        addLocations(33.9447902, -108.27880299999998);
        addLocations(16.0230659, -108.3955489);
        addLocations(16.015825, -108.2835862);
        addLocations(16.1044575, -108.1617063);
        addLocations(16.14904009999999, -108.28404849999998);
        addLocations(16.1411467, -108.2581995);
        addLocations(16.0632081, -108.2041049);
        addLocations(16.0440835, -108.254145);
        addLocations(16.0622501, -108.35555620000002);
        addLocations(16.01707689999999, -108.2886818);
        addLocations(16.0632081, -108.2041049);
        addLocations(16.1486202, -108.28121320000002);
        addLocations(16.053697, -108.40171599999996);
        addLocations(16.1012814, -108.31296900000001);
        addLocations(16.0667443, -108.4007231);
        addLocations(16.1279922, -108.1559945);
        addLocations(16.0632081, -108.2041049);
        addLocations(16.095153, -108.1276367);
        addLocations(16.0532024, -108.3836728);
        addLocations(16.15830049999999, -108.23985160000002);
        addLocations(16.1490401, -108.2840485);
        addLocations(16.0532024, -108.3836728);
        addLocations(16.070926, -108.40338700000001);
        addLocations(16.0424905, -108.3641055);
        addLocations(16.0167667, -108.316676);
        addLocations(16.0632081, -108.2041049);
        addLocations(16.0566067, -108.24893250000002);
        addLocations(16.02128249999999, -108.383893);
        addLocations(16.0115008, -108.1661894);
        addLocations(16.0717892, -108.36057249999999);
        addLocations(16.0921667, -108.12561749999998);
        addLocations(16.1462414, -108.15923829999997);
        addLocations(16.01707689999999, -108.2886818);
        addLocations(16.1281815, -108.27122610000004);
        addLocations(16.1553975, -108.30057850000003);
        addLocations(16.1281815, -108.2712261);
        addLocations(16.05026869999999, -108.25504330000001);
        addLocations(16.1360331, -108.352791);
        addLocations(16.1573164, -108.28996899999999);
        addLocations(16.0612412, -108.39881839999998);
        addLocations(16.1011667, -108.3331053);
        addLocations(16.1025903, -108.1600444);
        addLocations(33.9451969, -108.1824516);
        addLocations(33.9705223, -108.28014139999999);
        addLocations(16.0508937, -108.2483757);
        addLocations(16.14888289999999, -108.25417270000003);
        addLocations(16.0314858, -108.4104719);
        addLocations(16.043125, -108.26711799999998);
        addLocations(16.0880775, -108.208078);
        addLocations(16.0753309, -108.37749329999997);
        addLocations(16.14904009999999, -108.28404849999998);
        addLocations(16.0632081, -108.2041049);
        addLocations(16.083017, 108.389515000002);
        return latLngsArrayList;
    }

    private static void addLocations(double latitude, double longitude) {
        MyLocation location = new MyLocation(latitude, longitude);
        latLngsArrayList.add(location);
    }
}
