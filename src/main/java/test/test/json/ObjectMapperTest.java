package test.test.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapperTest {

    @Test
    public void tojson() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
    }

    @Test
    public void parseNull() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        int anInt = objectMapper.readTree(str).asInt();
        System.out.println(anInt);
    }

    @Test
    public void parse() throws JsonProcessingException {
        String str = "{\"a\":\"b\",\"c\":\"d\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(str, Map.class);
        System.out.println(map.get("a"));
    }

    @Test
    public void parse2() throws JsonProcessingException {
        String str = "{\n" +
                "   \"data\":    {\n" +
                "      \"TopPublicFolderId\": 1,\n" +
                "      \"TopPersonalFolderId\": 131\n" +
                "   },\n" +
                "   \"dataDescription\": \"\",\n" +
                "   \"result\": 0,\n" +
                "   \"message\": \"\",\n" +
                "   \"clientId\": null\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        JsonNode data = jsonNode.get("data22");
        JsonNode topPersonalFolderId = data.get("TopPersonalFolderId");
        String string = topPersonalFolderId.toString();
        System.out.println(string);
    }

    @Test
    public void parse3() throws JsonProcessingException {
        String str =
                "{\n" +
                        "   \"data\":    {\n" +
                        "      \"EnabledOutSend\": false,\n" +
                        "      \"FilesInfo\": [],\n" +
                        "      \"FoldersInfo\": [      {\n" +
                        "         \"FolderNamePath\": \"PublicRoot\\\\111项目附件\\\\税收计算\",\n" +
                        "         \"FolderId\": 135,\n" +
                        "         \"ParentFolderId\": 134,\n" +
                        "         \"FolderCode\": \"\",\n" +
                        "         \"FolderSortOrder\": 0,\n" +
                        "         \"FolderName\": \"税收计算\",\n" +
                        "         \"FolderPath\": \"1\\\\134\\\\135\",\n" +
                        "         \"FolderSize\": 0,\n" +
                        "         \"FolderMaxFolderSize\": 0,\n" +
                        "         \"FolderAlertSize\": 0,\n" +
                        "         \"FolderMaxFileSize\": 0,\n" +
                        "         \"FolderForbiddenFileExtensions\": null,\n" +
                        "         \"FolderChildFoldersCount\": 2,\n" +
                        "         \"FolderChildFilesCount\": 0,\n" +
                        "         \"SecurityLevelId\": 0,\n" +
                        "         \"FolderState\": 0,\n" +
                        "         \"FolderLockCount\": 0,\n" +
                        "         \"FolderCreateTime\": \"2024-02-23 16:27:45\",\n" +
                        "         \"FolderCreateOperator\": 24,\n" +
                        "         \"FolderCreateOperatorName\": \"kingdee01\",\n" +
                        "         \"FolderModifyTime\": \"2024-02-23 16:27:55\",\n" +
                        "         \"FolderModifyOperator\": 24,\n" +
                        "         \"FolderModifyOperatorName\": \"kingdee01\",\n" +
                        "         \"FolderArchiveTime\": \"0001-01-01 00:00:00\",\n" +
                        "         \"FolderArchiveOperator\": 0,\n" +
                        "         \"FolderPermData\": 2140907969,\n" +
                        "         \"FolderOtherValue\": null,\n" +
                        "         \"FolderDeletedTime\": \"0001-01-01 00:00:00\",\n" +
                        "         \"FolderDeletedBy\": null,\n" +
                        "         \"FolderRelativePath\": null,\n" +
                        "         \"FolderType\": 1,\n" +
                        "         \"FolderRemark\": \"\",\n" +
                        "         \"IsDeleted\": false,\n" +
                        "         \"RecentPathOne\": null,\n" +
                        "         \"RecentPathTwo\": null,\n" +
                        "         \"RecentPathThree\": null,\n" +
                        "         \"RecentPathFour\": null,\n" +
                        "         \"RecentPathFive\": null,\n" +
                        "         \"IsFavorite\": false,\n" +
                        "         \"SharePermission\": 0,\n" +
                        "         \"DiscussCount\": 0\n" +
                        "      }],\n" +
                        "      \"InfoItems\":       [\n" +
                        "                  {\n" +
                        "            \"DataType\": \"docName\",\n" +
                        "            \"Name\": \"basic:name\",\n" +
                        "            \"Remark\": null,\n" +
                        "            \"Title\": \"name\"\n" +
                        "         },\n" +
                        "                  {\n" +
                        "            \"DataType\": \"datetime\",\n" +
                        "            \"Name\": \"basic:modifyTime\",\n" +
                        "            \"Remark\": null,\n" +
                        "            \"Title\": \"modifyTime\"\n" +
                        "         },\n" +
                        "                  {\n" +
                        "            \"DataType\": \"string\",\n" +
                        "            \"Name\": \"basic:editor\",\n" +
                        "            \"Remark\": null,\n" +
                        "            \"Title\": \"editor\"\n" +
                        "         },\n" +
                        "                  {\n" +
                        "            \"DataType\": \"fileSize\",\n" +
                        "            \"Name\": \"basic:size\",\n" +
                        "            \"Remark\": null,\n" +
                        "            \"Title\": \"size\"\n" +
                        "         },\n" +
                        "                  {\n" +
                        "            \"DataType\": \"version\",\n" +
                        "            \"Name\": \"basic:version\",\n" +
                        "            \"Remark\": null,\n" +
                        "            \"Title\": \"version\"\n" +
                        "         }\n" +
                        "      ],\n" +
                        "      \"IsArchive\": false,\n" +
                        "      \"MustOnline\": true,\n" +
                        "      \"SecurityEnable\": false,\n" +
                        "      \"Settings\":       {\n" +
                        "         \"PageNum\": 1,\n" +
                        "         \"PageSize\": 100,\n" +
                        "         \"TotalCount\": 1,\n" +
                        "         \"ViewMode\": null,\n" +
                        "         \"DocViewId\": 0\n" +
                        "      },\n" +
                        "      \"ThisFolder\": null\n" +
                        "   },\n" +
                        "   \"dataDescription\": \"\",\n" +
                        "   \"result\": 0,\n" +
                        "   \"message\": \"\",\n" +
                        "   \"clientId\": null\n" +
                        "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        JsonNode data = jsonNode.get("data");
        JsonNode FoldersInfo = data.get("FoldersInfo");
        int size = FoldersInfo.size();
        for (int i = 0; i < size; i++) {
            JsonNode folder = FoldersInfo.get(i);
            JsonNode folderName = folder.get("FolderName");
            if ("税收计算".equals(folderName.asText())) {
                System.out.println(folder.get("FolderId").asInt());
            }
        }
    }

    @Test
    public void test4() throws JsonProcessingException {

        String str = "{\n" +
                "  \"data\": {\n" +
                "    \"FileId\": 736,\n" +
                "    \"FileGuid\": \"65067abc-5857-4d52-a250-de7d725ad098\",\n" +
                "    \"FileName\": \"【財稅審計處處長審批】123-DAFC-2024 差異調整建議書 (6).pdf\",\n" +
                "    \"FileSize\": 497633,\n" +
                "    \"Code\": \"\",\n" +
                "    \"FileModifyTime\": \"2024-09-13 16:33:46\",\n" +
                "    \"EditorName\": \"金蝶稅收\",\n" +
                "    \"FileCreateTime\": \"2024-09-13 16:33:46\",\n" +
                "    \"CreatorId\": 68,\n" +
                "    \"CreatorName\": \"金蝶稅收\",\n" +
                "    \"FileCreateOperatorName\": \"金蝶稅收\",\n" +
                "    \"CurrentOperator\": null,\n" +
                "    \"CurrentOperatorId\": 0,\n" +
                "    \"CurrentVersionId\": 0,\n" +
                "    \"LastVersionId\": 749,\n" +
                "    \"FileCurVerNumStr\": \"\",\n" +
                "    \"FileLastVerNumStr\": \"0.1\",\n" +
                "    \"FileState\": 0,\n" +
                "    \"FileRemark\": \"\",\n" +
                "    \"ParentFolderId\": 714,\n" +
                "    \"FilePath\": \"1\\\\469\\\\638\\\\671\\\\711\\\\712\\\\714\\\\\",\n" +
                "    \"FileNamePath\": \"PublicRoot\\\\博监附件\\\\差异调整\\\\美高梅金殿超濠股份有限公司\\\\博彩桌\\\\rbt-20240913-1631\\\\DICJ批覆文件\",\n" +
                "    \"IncId\": null,\n" +
                "    \"FileArchiveTime\": \"2024-09-13 16:33:46\",\n" +
                "    \"Permission\": 1088391993,\n" +
                "    \"FileType\": 2,\n" +
                "    \"IsDeleted\": false,\n" +
                "    \"SecurityLevelId\": 0,\n" +
                "    \"SecLevelName\": \"\",\n" +
                "    \"SecLevelDegree\": -1,\n" +
                "    \"EffectiveTime\": \"2024-09-13 16:33:46\",\n" +
                "    \"ExpirationTime\": \"2024-09-13 16:33:46\",\n" +
                "    \"IsFavorite\": false,\n" +
                "    \"FileCipherText\": false,\n" +
                "    \"IsCodeRules\": false,\n" +
                "    \"FileExtName\": \".pdf\",\n" +
                "    \"RelateMode\": 0,\n" +
                "    \"CanPreview\": false,\n" +
                "    \"CanDownload\": false,\n" +
                "    \"CanDeleteFile\": false,\n" +
                "    \"AttachType\": 0,\n" +
                "    \"FileLastVerExtName\": \".pdf\"\n" +
                "  },\n" +
                "  \"dataDescription\": \"\",\n" +
                "  \"result\": 0,\n" +
                "  \"message\": \"\",\n" +
                "  \"clientId\": null\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.readTree(str);
//        String text = responseNode.get("data").get("FileName").asText();
        String text = responseNode.get("data").get("fileName").asText();
        System.out.println(text);
    }

    @Test
    public void test5() throws IOException {
        String str = FileUtils.readFileToString(new File("D:\\intellijProject\\unittest\\src\\main\\java\\org\\example\\test\\json\\text\\response.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.readTree(str);
        JsonNode jsonNode = responseNode.get("data");
        JsonNode filesInfoNode = jsonNode.get("FilesInfo");
        ArrayList<Integer> result = new ArrayList<>();
        filesInfoNode.forEach(e -> {
            result.add(e.get("FileId").asInt());
        });
        System.out.println(result.toString());
    }

    @Test
    public void test6() throws JsonProcessingException {
        String Str = "{\"MetaDataProperty\":[]}";
        JsonNode MetaDataPropertyNode = new ObjectMapper().readTree(Str).get("MetaDataProperty").get(0);
        if (MetaDataPropertyNode == null) {
            System.out.println("empty");
        }
    }

    @Test
    public void test7() throws JsonProcessingException {
        String response = "{data=31, dataDescription=data表示文件夹ID，-1表示不存在, result=0, message=, clientId=null}";
        ObjectMapper objectMapper = new ObjectMapper();
        int anInt = objectMapper.readTree(response).get("data").asInt();
        System.out.println(anInt);
    }

    @Test
    public void test8() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", 456);
        map.put("478", "45656");
        map.put("gg", "45623");
        String s = new ObjectMapper().writeValueAsString(map);
        System.out.println(s);
    }

}
