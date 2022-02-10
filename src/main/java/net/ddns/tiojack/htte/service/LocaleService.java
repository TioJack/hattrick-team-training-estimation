package net.ddns.tiojack.htte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class LocaleService {

    public boolean update() {
        //        final ObjectMapper mapper = new ObjectMapper();
//
//        final List<String> props = List.of(
//                "player",
//                "player_abbr",
//                "age",
//                "age_abbr",
//                "specialty",
//                "specialty_abbr",
//                "leadership",
//                "leadership_abbr",
//                "experience",
//                "experience_abbr",
//                "stamina",
//                "stamina_abbr",
//                "form",
//                "form_abbr",
//                "loyalty",
//                "loyalty_abbr",
//                "motherClubBonus",
//                "motherClubBonus_abbr",
//                "playmaking",
//                "playmaking_abbr",
//                "winger",
//                "winger_abbr",
//                "scoring",
//                "scoring_abbr",
//                "passing",
//                "passing_abbr",
//                "defending",
//                "defending_abbr",
//                "keeper",
//                "keeper_abbr",
//                "set_pieces",
//                "set_pieces_abbr");
//
//        final String outFolder = "C:\\Users\\barts\\Documents\\hattrick-team-training-estimation\\src\\main\\resources\\static\\locale\\";
//        final String folder = "C:\\Users\\barts\\Downloads\\foxtrick\\content\\locale";
//        final File directory = new File(folder);
//        final File[] contents = directory.listFiles();
//        for (final File f : contents) {
//            //System.out.println(f.getAbsolutePath());
//            try {
//                final File f2 = new File(f.getAbsolutePath() + "\\foxtrick.properties");
//                final File f3 = new File(outFolder + f.getName() + ".json");
//                if (!f3.createNewFile()) {
//                    f3.delete();
//                    f3.createNewFile();
//                }
//                final FileWriter myWriter = new FileWriter(f3);
//                myWriter.write("{" + System.lineSeparator());
//                final Scanner myReader = new Scanner(f2);
//                while (myReader.hasNextLine()) {
//                    final String data = myReader.nextLine();
//                    //System.out.println(data);
//                    props.forEach(prop -> {
//                        if (data.toLowerCase().startsWith(prop.replace("_abbr", ".abbr").concat("=").toLowerCase())) {
//                            try {
//                                final String[] str = data.split("=");
//                                myWriter.write(String.format("  \"%s\": \"%s\",%s", prop, str[1], System.lineSeparator()));
//                            } catch (final IOException ignored) {
//                            }
//                        }
//                    });
//                }
//                myReader.close();
//                final Object htlang = mapper.readValue(new File(f.getAbsolutePath() + "\\htlang.json"), Object.class);
//                final List<Object> specialties = ((ArrayList) ((LinkedHashMap) ((LinkedHashMap) htlang).get("language")).get("specialties"));
//                myWriter.write("  \"specialties\": {" + System.lineSeparator());
//                myWriter.write(String.format("    \"none\": \"%s\",%s", ((LinkedHashMap) specialties.get(0)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"technical\": \"%s\",%s", ((LinkedHashMap) specialties.get(1)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"quick\": \"%s\",%s", ((LinkedHashMap) specialties.get(2)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"powerful\": \"%s\",%s", ((LinkedHashMap) specialties.get(3)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"unpredictable\": \"%s\",%s", ((LinkedHashMap) specialties.get(4)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"head_specialist\": \"%s\",%s", ((LinkedHashMap) specialties.get(5)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"resilient\": \"%s\",%s", ((LinkedHashMap) specialties.get(6)).get("value"), System.lineSeparator()));
//                myWriter.write(String.format("    \"support\": \"%s\"%s", ((LinkedHashMap) specialties.get(7)).get("value"), System.lineSeparator()));
//                myWriter.write("  }" + System.lineSeparator());
//                myWriter.write("}" + System.lineSeparator());
//                myWriter.close();
//            } catch (final IOException ignored) {
//            }
//        }

        final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ;

        final Map<String, String> props = Map.of(
                "button.save", "save",
                "button.import", "load");

        //final String outFolder = "C:\\Users\\barts\\Documents\\hattrick-team-training-estimation\\src\\main\\resources\\static\\locale\\";
        //final String folder = "C:\\Users\\barts\\Downloads\\foxtrick\\content\\locale";
        final String outFolder = "/home/t.solivellas/IdeaProjects/hattrick-team-training-estimation/src/main/resources/static/locale/";
        final String folder = "/home/t.solivellas/IdeaProjects/foxtrick/content/locale";
        final File directory = new File(folder);
        final File[] contents = directory.listFiles();
        for (final File f : contents) {
            //System.out.println(f.getAbsolutePath());
            try {
                final File f2 = new File(f.getAbsolutePath() + "/foxtrick.properties");
                final File f3 = new File(outFolder + f.getName() + ".json");
                final Object actual = mapper.readValue(f3, Object.class);
                if (!f3.createNewFile()) {
                    f3.delete();
                    f3.createNewFile();
                }
                final Scanner myReader = new Scanner(f2);
                while (myReader.hasNextLine()) {
                    final String data = myReader.nextLine();
                    //System.out.println(data);
                    props.forEach((k, v) -> {
                        if (data.toLowerCase().startsWith(k.replace("_abbr", ".abbr").concat("=").toLowerCase())) {
                            final String[] str = data.split("=");
                            ((LinkedHashMap) actual).put(v, str[1]);
                        }
                    });
                }
                myReader.close();
                final FileWriter myWriter = new FileWriter(f3, true);
                myWriter.write(mapper.writeValueAsString(actual));
                myWriter.close();
            } catch (final IOException ignored) {
            }
        }
        return true;
    }

}
