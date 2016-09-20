<cfcomponent displayname="Icap" output="false">
  <!--- The default port --->
  <cfset variables.defaultPort = 1344 />

  <!---
    Scans the specified file using the ICAP protocol

    @param server the address of ICAP server to which the file is to be sent
    @param port the port on which the ICAP service is listening
    @param service the ICAP service that will scan the file
    @param file the file to be scanned
  --->
  <cffunction name="scanFile" access="public" returntype="struct" output="false">
    <cfargument name="server" type="string" required="true" />
    <cfargument name="port" type="numeric" required="true" default="#variables.defaultPort#" />
    <cfargument name="service" type="string" required="true" />
    <cfargument name="file" type="string" required="true" />

    <!--- Define local variables --->
    <cfset var icapUtils = createObject("java", "org.primeoservices.cficap.IcapUtils") />
    <cfset var response = "" />
    <cfset var statusLine = "" />
    <cfset var i = "" />
    <cfset var header = "" />
    <cfset var result = structNew() />

    <cftry>
      <!--- Send file to ICAP service --->
      <cfset response = icapUtils.scanFile(arguments.server, arguments.port, arguments.service, arguments.file) />
      <cfset statusLine = response.getStatusLine() />

      <!--- Build result --->
      <cfset result.errorDetail = "" />
      <cfset result.statusCode = statusLine.getCode() & " " & statusLine.getText() />

      <!--- Add headers --->
      <cfset result.responseHeader = structNew() />
      <cfloop array="#response.getHeaders()#" index="i" item="header">
        <cfset structInsert(result.responseHeader, header.getName(), header.getValue()) />
      </cfloop>

      <!--- Add additional info --->
      <cfset result.icap_version = statusLine.getProtocolVersion().toString() />
      <cfset result.status_code = statusLine.getCode() />
      <cfset result.status_text = statusLine.getText() />

      <!--- Handle errors --->
      <cfcatch type="any">
        <cfset result.errorDetail = cfcatch.message />
        <cfset result.responseHeader = structNew() />
        <cfset result.statusCode = "Connection Failure. Status code unavailable." />
      </cfcatch>
    </cftry>

    <cfreturn result />
  </cffunction>
</cfcomponent>
