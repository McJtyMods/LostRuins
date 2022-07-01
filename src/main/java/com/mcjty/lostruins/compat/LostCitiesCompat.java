package com.mcjty.lostruins.compat;

import com.mcjty.lostruins.LostRuins;
import mcjty.lostcities.api.ILostCities;
import mcjty.lostcities.api.ILostCitiesPre;
import mcjty.lostcities.api.ILostCityProfile;
import net.minecraftforge.fml.InterModComms;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.function.Function;

public class LostCitiesCompat
{
    public static ILostCities lostCities;

    public static void setupLostCitiesPre() {
        InterModComms.sendTo(ILostCities.LOSTCITIES, ILostCities.GET_LOST_CITIES_PRE, GetLostCitiesPre::new);
    }

    public static void setupLostCities() {
        InterModComms.sendTo(ILostCities.LOSTCITIES, ILostCities.GET_LOST_CITIES, GetLostCities::new);
    }

    public static class GetLostCitiesPre implements Function<ILostCitiesPre, Void> {
        @Nullable
        @Override
        public Void apply(ILostCitiesPre lc) {
            lc.registerProfileSetupCallback(profileSetup -> {
                ILostCityProfile profile = profileSetup.createProfile("ruined", "ancient");
                profile.setDescription("Very ruined cities (Lost Ruins)");
                profile.setWorldStyle("ruined");
                profile.setCityChancle(0.003);
                profile.setRuinChance(0.7f, 0.1f, 0.7f);
                int offset = 2;
                profile.setGroundLevel(71-offset);
                profile.setCityLevelHeights(75-offset, 83-offset, 91-offset, 99-offset);
                profile.setOceanCorrectionBorder(0);
            });
            return null;
        }
    }

    public static class GetLostCities implements Function<ILostCities, Void> {
        @Nullable
        @Override
        public Void apply(ILostCities lc) {
            lostCities = lc;

            String path = "/assets/lostruins/citydata/ruinedstyle.json";
            try(InputStream inputstream = LostRuins.class.getResourceAsStream(path)) {
                lostCities.loadAsset(inputstream, "ruinedstyle.json");
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }

            return null;
        }
    }
}
