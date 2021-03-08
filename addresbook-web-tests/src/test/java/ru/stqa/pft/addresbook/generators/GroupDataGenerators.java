package ru.stqa.pft.addresbook.generators;

import ru.stqa.pft.addresbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GroupDataGenerators {
    public static void main(String[] args) throws IOException{
        int count = Integer.parseInt(args[0]);
        File file = new File(args[0]);

        List<GroupData> groups = generateGroups(count);
        save(groups, file);
    }

    private static void save(List<GroupData> groups, File file) throws IOException {
            Writer writer = new FileWriter(file);
            for(GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n",
                        group.getName(),
                        group.getHeader(),
                        group.getFooter()));
            }
            writer.close();
    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add((new GroupData()
                    .withName(String.format("test %s", i)))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
