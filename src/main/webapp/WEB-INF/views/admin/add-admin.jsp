<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card shadow border-0">
                <div class="card-body p-5">
                    <h2 class="fw-bold mb-4 text-center">Add New Admin</h2>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="action" value="add">

                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" name="username" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Role</label>
                            <select name="role" class="form-select">
                                <option value="ADMIN">ADMIN</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Add Admin</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>