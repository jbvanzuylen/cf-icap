<cffunction name="icapScanFile" returntype="struct" output="false">
  <cfargument name="server" type="string" required="true" />
  <cfargument name="port" type="numeric" required="false" />
  <cfargument name="service" type="string" required="true" />
  <cfargument name="file" type="string" required="true" />

  <!--- Create a new pass with the specified style --->
  <cfreturn createObject("component", "icap.Icap").scanFile(argumentCollection = arguments) />
</cffunction>
