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
    <title>Schoology - Class</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    <script src="js/app.js"></script>
</head>
<body>
<jsp:include page="templates/nav.jsp"/>
<div class="w-90 m-4">
    <button type="button" class="btn btn-success btn-lg btn-block w-100 " data-bs-toggle="modal" data-bs-target="#newClassModal">New Class</button>
</div>

<div class="row justify-content-center w-90 m-4" >
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th scope="col" class="text-center"># Class Code</th>
            <th scope="col" class="text-center">Title</th>
            <th scope="col" class="text-center">Description</th>
            <th scope="col" class="text-center">Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listClass}" var="classes">
            <tr id="tr-${classes.code}">
                <th scope="row" class="text-center">${classes.code}</th>
                <td class="text-center">${classes.classTitle}</td>
                <td class="text-center w-50 wm-50">${classes.classDescription}</td>
                <td class="text-center">
                    <!-- Button trigger modal -->
                    <button id="${classes.code}" type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editModal" onclick="class_edit(this)">
                        Edit
                    </button>
                    <button id="${classes.code}" type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#assignedClassesModal" onclick="ajax_get_students_by_class(this)">See students</button>
                    <form action="class/delete" method="post" class="d-inline">
                        <input type="text" name="code" value="${classes.code}" hidden>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Modal Edit class -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit Form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="class/update" method="post">
                    <input type="text" id="txtClassCode" name="code" hidden>
                    <div class="form-group">
                        <label for="txtClassTitle">Title</label>
                        <input type="text" class="form-control" id="txtClassTitle" name="classTitle">
                    </div>
                    <div class="form-group">
                        <label for="txtClassDescription">Description</label>
                        <textarea class="form-control" id="txtClassDescription" rows="3" name="classDescription"></textarea>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-warning">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal New class -->
<div class="modal fade" id="newClassModal" tabindex="-1" aria-labelledby="newStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newClassModalLabel">Registration Form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="class/add" method="post">
                    <div class="form-group">
                        <label for="txtNewClassCode">Code</label>
                        <input type="text" class="form-control" id="txtNewClassCode" name="code">
                    </div>
                    <div class="form-group">
                        <label for="txtNewClassTitle">Title</label>
                        <input type="text" class="form-control" id="txtNewClassTitle" name="classTitle">
                    </div>
                    <div class="form-group">
                        <label for="txtNewClassDescription">Description</label>
                        <textarea class="form-control" id="txtNewClassDescription" rows="3" name="classDescription"></textarea>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal assigned classes -->
<div class="modal fade" id="assignedClassesModal" tabindex="-1" aria-labelledby="assignedClassesLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="assignedClassesLabel">Registered Students</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <ul id="list-students" class="list-group">

                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>
