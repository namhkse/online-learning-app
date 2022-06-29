package controller.management;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import dao.PermissionDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Permission;
import model.Role;
import util.RequestParamUtil;

@WebServlet(name = "RolePermissionController", urlPatterns = {"/management/rolepermission"})
public class RolePermissionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleDAO roleDAO = new RoleDAO();
        PermissionDAO permissionDAO = new PermissionDAO();
        List<Role> roles = roleDAO.findAll();
        List<Permission> permissions = permissionDAO.findAll();

        int roleId = RequestParamUtil.parseInt(req, "roleId");
        Optional<Role> optional = roles.stream().filter(r -> r.getId() == roleId).findAny();

        Role selectedRole = roles.isEmpty() ? null
                : (optional.isPresent() ? optional.get() : roles.get(0));

        List<Permission> selectedPermission = permissionDAO.findPermissionOfRole(selectedRole.getId());

        req.setAttribute("selectedPermission", selectedPermission);
        req.setAttribute("selectedRole", selectedRole);
        req.setAttribute("roles", roles);
        req.setAttribute("permissions", permissions);
        req.getRequestDispatcher("../view/role-permission.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleName = req.getParameter("inputRoleName");
        int roleId = new RoleDAO().save(new Role(0, roleName));
        resp.sendRedirect("./rolepermission?roleId=" + roleId);
    }

    /**
     * Json format { "roleId: 1, "permissionIds": [1, 2, 3 , 4, 5] }
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        try {
            List<Integer> permissionIds = null;
            int roleId = 0;

            reader.beginObject();

            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("roleId")) {
                    roleId = reader.nextInt();
                } else if (name.equals("permissionIds") && reader.peek() != JsonToken.NULL) {
                    permissionIds = readIntegerArray(reader);

                }
            }
            reader.endObject();

            PermissionDAO permissionDAO = new PermissionDAO();
            permissionDAO.deletePermissionOfRole(roleId);
            if (permissionIds != null) {
                for (int permissionId : permissionIds) {
                    permissionDAO.assignPermissionToRole(permissionId, roleId);
                }
            }

        } finally {
            reader.close();
        }
    }

    public List<Integer> readIntegerArray(JsonReader reader) throws IOException {
        List<Integer> ints = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            ints.add(reader.nextInt());
        }
        reader.endArray();
        return ints;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId = RequestParamUtil.parseInt(req, "roleId");
        try {
            RoleDAO roleDAO = new RoleDAO();
            roleDAO.delete(new Role(roleId, ""));
            resp.setStatus(200);
        } catch (Exception ex) {
            resp.setStatus(404);
            ex.printStackTrace();
        }
    }

}
