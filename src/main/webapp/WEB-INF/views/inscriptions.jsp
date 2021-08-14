<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gary
  Date: 13/8/2021
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schoology - Inscriptions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="templates/nav.jsp"/>
<div class="w-90 m-4">
    <button type="button" class="btn btn-success btn-lg btn-block w-100 " data-bs-toggle="modal" data-bs-target="#newInscriptionModal">New Inscription</button>
</div>

<div class="row justify-content-center w-90 m-4" >
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th scope="col" class="text-center"># Inscription ID</th>
            <th scope="col" class="text-center">Student ID</th>
            <th scope="col" class="text-center">Code Class</th>
            <th scope="col" class="text-center">Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listInscriptions}" var="inscription">
            <tr id="tr-${inscription.id_inscription}">
                <th scope="row" class="text-center">${inscription.id_inscription}</th>
                <td class="text-center">${inscription.studentId}</td>
                <td class="text-center">${inscription.code}</td>
                <td class="text-center">
                    <form action="inscriptions/delete" method="post" class="d-inline">
                        <input type="text" name="id_inscription" value="${inscription.id_inscription}" hidden>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- Modal New inscriptions -->
<div class="modal fade" id="newInscriptionModal" tabindex="-1" aria-labelledby="newInscriptionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newInscriptionModalLabel">Registration Form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="inscriptions/add" method="post">
                    <input type="text" id="txtNewIdInscription" name="id_inscription" value="-1" hidden>
                    <div class="form-group">
                        <label for="txtNewStudentId">Student Code</label>
                        <input type="text" class="form-control" id="txtNewStudentId" name="studentId">
                    </div>
                    <div class="form-group">
                        <label for="txtNewCode">Class Code</label>
                        <input type="text" class="form-control" id="txtNewCode" name="code">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success">Register</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
