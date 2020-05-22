<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
{
	"csrf_token":"${_csrf.token}",
	"csrf_headerName":"${_csrf.headerName}",
	"csrf_parameterNam":"${_csrf.parameterName}",
	"username" : "<sec:authentication property="name"/>"
}