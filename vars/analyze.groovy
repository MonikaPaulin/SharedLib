import org.apache.commons.csv.*

import java.util.stream.*

@NonCPS

def call()

{


String[] HEADERS = ["AppID","AppName","Environment","ReleaseVersion","Status"]

String outputHeader="App ID,App Name,Release Version,Environments Passed,Environment Failed,Comments";


Reader filereader = new FileReader("$WORKSPACE\\Input.csv");

Iterable<CSVRecord> records = CSVFormat.DEFAULT

		.withHeader(HEADERS)

		.withFirstRecordAsHeader()

		.parse(filereader);



StringBuilder output = new StringBuilder();

output.append(outputHeader);



Map<String, List<CSVRecord>> recordFiltered =   StreamSupport

		.stream(records.spliterator(), false).

		collect(Collectors.groupingBy({record -> record.get("AppID")+"-"+record.get("AppName")+"-"+record.get("ReleaseVersion")} ));

		

for (Map.Entry<String, List<CSVRecord>> entry : recordFiltered.entrySet()) {


	List<CSVRecord> buildList = entry.getValue();	

	List<CSVRecord> failedList = buildList.stream().filter({f -> f.get("Status").contains("Failed")})

	.collect(Collectors.toList());

	

	List<CSVRecord> passedList = buildList.stream().filter({f -> !f.get("Status").contains("Passed")})

	.collect(Collectors.toList());

	

	String delimitter = ",";

	String passedEnvList = "NA";

	String failedEnvList = "NA";

	String comments = "NA";

	

	if (passedList != null && passedList.size() > 0) {

		passedEnvList = passedList.stream().map({mp -> mp.get("Environment")}).collect(Collectors.joining("|"));

		comments = passedList.stream().map({mp -> String.format(mp.get("Status"))}).collect(Collectors.toList()).get[0];

	}

	if (failedList != null && failedList.size() > 0) {

		failedEnvList = failedList.stream().map({mp -> mp.get("Environment")}).collect(Collectors.joining("|"));

		comments = failedList.stream().map({mp -> String.format(mp.get("Status"))}).collect(Collectors.toList()).get[0];
;
	}

	

	output.append('\n');

	output.append(buildList.get(0).get("AppID") + delimitter);

	output.append(buildList.get(0).get("AppName") + delimitter);

	output.append(buildList.get(0).get("ReleaseVersion") + delimitter);

	output.append(passedEnvList + delimitter);

	output.append(failedEnvList + delimitter);

	output.append(comments);



}



PrintWriter writer;

try {

	writer = new PrintWriter(new File("$WORKSPACE\\Release_Status.csv"))

	writer.write(output.toString());

	System.out.println("done!");

	println("##########################################")

	println ("${output}")

	println("##########################################")

}



catch (FileNotFoundException e) {

	System.out.println(e.getMessage());

}

finally {

	writer.close();

}



}

