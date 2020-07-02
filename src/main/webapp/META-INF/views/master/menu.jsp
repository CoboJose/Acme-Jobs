<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>

		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">

			<acme:menu-suboption code="master.menu.anonymous.announcement" action="/anonymous/announcement/list" />
			<acme:menu-suboption code="master.menu.anonymous.company-records.list" action="/anonymous/company-records/list" />
			<acme:menu-suboption code="master.menu.anonymous.company-records.list.top" action="/anonymous/company-records/list-fivestars" />
			<acme:menu-suboption code="master.menu.anonymous.investor-record" action="/anonymous/investor-record/list" />
			<acme:menu-suboption code="master.menu.anonymous.investor-record.top" action="/anonymous/investor-record/list-fivestars" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">

			<acme:menu-suboption code="master.menu.authenticated.announcement" action="/authenticated/announcement/list" />
			<acme:menu-suboption code="master.menu.authenticated.company-records.list" action="/authenticated/company-records/list" />
			<acme:menu-suboption code="master.menu.authenticated.investor-record" action="/authenticated/investor-record/list" />
			<acme:menu-suboption code="master.menu.authenticated.message-thread.list" action="/authenticated/message-thread/list-mine" />

			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list" />
			<acme:menu-suboption code="master.menu.authenticated.job.list" action="/authenticated/job/list" />

		</acme:menu-option>


		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list" />
			<acme:menu-suboption code="master.menu.administrator.banner" action="/administrator/banner/list" />

			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.customisationParameters.show"
				action="/administrator/customisation-parameters/show" />

			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.companyRecords" action="/administrator/company-records/list" />
			<acme:menu-suboption code="master.menu.administrator.investor-record" action="/administrator/investor-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.request-auditor" action="/administrator/request-auditor/list" />
		</acme:menu-option>


		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			
      <acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list-mine" />
			<acme:menu-suboption code="master.menu.employer.application.list-mine" action="/employer/application/list-to-my-jobs" />

			<acme:menu-suboption code="master.menu.employer.application.list-mine.groupByRef" action="/employer/application/list-mine-by-reference" />
			<acme:menu-suboption code="master.menu.employer.application.list-mine.groupByStat" action="/employer/application/list-mine-by-status" />
			<acme:menu-suboption code="master.menu.employer.application.list-mine.groupByCreation" action="/employer/application/list-mine-by-creation" />			
      
      <acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create" />
      
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list-mine" action="/worker/application/list-mine" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.auditRecords.list" action="/auditor/job/list-mine" />
			<acme:menu-suboption code="master.menu.auditor.auditRecords.listJobs" action="/auditor/job/list" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.banner.list" action="/sponsor/banner/list-mine" />
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/show"
				access="hasRole('Auditor')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />
	
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update"
				access="hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update"
				access="hasRole('Worker')" />
		<acme:check-access test="!hasRole('Auditor')">
				<acme:menu-suboption code="master.menu.user-account.become-request-auditor" action="/authenticated/request-auditor/create"
					access="!hasRole('RequestAuditor')" />
		</acme:check-access>

		<acme:check-access test="!hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.user-account.request-auditor" action="/authenticated/request-auditor/show"
					access="hasRole('RequestAuditor')" />
		</acme:check-access>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create"
				access="!hasRole('Sponsor')" />    	
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>