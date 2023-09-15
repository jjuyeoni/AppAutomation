package testrail;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestResult;
public class TestRailAPI {
    private APIClient client;
    private int runId;
    private static TestRailAPI trInstance;

    public void connectTestRail(String trAddress, String trUser, String trPassword) {
        client = new APIClient(trAddress);
        client.setUser(trUser);
        client.setPassword(trPassword);
    }

    public static TestRailAPI getInstance() {
        if (trInstance == null) {
            trInstance = new TestRailAPI();
        }
        return trInstance;
    }

    public void setTestRailRun(String projectName, String runName) {
        try {
            // 프로젝트 ID 가져오기
            Object projectResponse = client.sendGet("get_projects");
            JSONObject projects = (JSONObject) projectResponse;
            JSONArray projectArray = (JSONArray) projects.get("projects");
            JSONObject project = (JSONObject) projectArray.stream()
                    .filter(p -> ((String) ((JSONObject) p).get("name")).equals(projectName))
                    .findFirst()
                    .orElse(null);
            long projectId = (long) project.get("id");

            // 테스트 실행 생성
            JSONObject runData = new JSONObject();
            runData.put("name", runName);
            Object addRunResponse = client.sendPost("add_run/" + projectId, runData);
            JSONObject addedRun = (JSONObject) addRunResponse;
            runId = ((Long) addedRun.get("id")).intValue(); // runId 설정

            System.out.println("TestRail Run 설정 완료. Run ID: " + runId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResult(int caseId, ITestResult result) {
        try {
            String status;
            if (result.getStatus() == ITestResult.SUCCESS) {
                status = "1"; // PASSED
            } else if (result.getStatus() == ITestResult.FAILURE) {
                status = "5"; // FAILED
            } else if (result.getStatus() == ITestResult.SKIP) {
                status = "2"; // BLOCKED
            } else {
                status = "4"; // RETEST
            }

            JSONObject resultData = new JSONObject();
            resultData.put("status_id", status);
            resultData.put("comment", "Automated test result");

            Object addResultResponse = client.sendPost("add_result_for_case/" + runId + "/" + caseId, resultData);
            JSONObject addedResult = (JSONObject) addResultResponse;
            //System.out.println("TestRail Result 업데이트 완료. Case ID: " + caseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
