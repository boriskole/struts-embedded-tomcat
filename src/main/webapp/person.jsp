<%@ taglib prefix="s" uri="/struts-tags" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Person Details</title>
        <style>
            table {
                border-collapse: collapse;
            }

            th, td {
                border: 1px solid black;
                padding: 10px 20px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h1>Person Details</h1>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Date of Birth</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="people">
                    <tr>
                        <td><s:property value="id"/></td>
                        <td><s:property value="name"/></td>
                        <td><s:property value="address"/></td>
                        <td>
                            <s:property value="dateOfBirth"/> (<s:property value="age"/> y/o)
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>
