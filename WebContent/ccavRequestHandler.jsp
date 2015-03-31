<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<%
	 String accessCode= "AVUQ04CC64AF66QUFA";		//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey = "18F62D2A438A259C8D85C9DB06C73485";
	 int merchant_id = 60380; 
	 //Put in the 32 Bit Working Key provided by CCAVENUES.  
	 Enumeration enumeration=request.getParameterNames();
	 Properties prop = new Properties();
	 InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Resources/Dependency.properties");
	 prop.load(resourceAsStream);
	 String ccaRequest="merchant_id="+merchant_id+ "&currency=INR&" +"language=EN&" + "redirect_url=" +prop.getProperty("redirecturl") +"cancel_url="+prop.getProperty("cancelurl"), pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = request.getParameter(pname);
	      ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
	 }
     out.println(ccaRequest);
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>
	<form id="nonseamless" method="post" name="redirect" action="https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction"/> 
		<input type="hidden" id="encRequest" name="encRequest" value="<%= encRequest %>">
		<input type="hidden" name="access_code" id="access_code" value="<%= accessCode %>">
		<input type="hidden" name="redirect_url" id="redirect_url" value="http://192.168.2.49:8081/MCPG_JSP_KIT_2/ccavResponseHandler.jsp">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
	
	
	
 </body> 
</html>