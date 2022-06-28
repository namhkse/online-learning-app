function customAlert(title, content, type) {
    $.alert({
        title: title,
        content: content,
        type: type,
        typeAnimated: true,
        buttons: {
            close: function () {
            }
        }
    });
}

$("#updateBtn").click(function () {
    let roleId = this.value;
    let permissionIds = [];
    $(".permission-check").each(function (e) {
        if (this.checked)
            permissionIds.push(this.value);
    });

    let data = {
        roleId: roleId,
        permissionIds: permissionIds
    }

    $.ajax({
        url: `./rolepermission`,
        type: 'PUT',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (data, textStatus, jqXHR) {
            customAlert("", "Update permission successfully!.", "green");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 403) {
                customAlert("Encountered an error!", "You are not allowed execute this action.", "red");
            } else {
                customAlert("Encountered an error!", "Update permission fail.", "red");
            }
        }
    });
});

$("#deleteBtn").click(function () {
    let roleId = this.value;
    $.confirm({
        title: 'Confirm',
        content: `Do you want to delete the role ${this.getAttribute("data-role-name")} ?`,
        buttons: {
            confirm: {
                btnClass: 'btn-blue',
                action: function () {
                    $.ajax({
                        url: `./rolepermission?roleId=${roleId}`,
                        type: 'DELETE',
                        success: function (data, textStatus, jqXHR) {
                            location.reload();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            if (jqXHR.status == 403) {
                                customAlert("Encountered an error!", "You are not allowed execute this action.", "red");
                            } else {
                                customAlert("Encountered an error!", "Delete role fail.", "red");
                            }
                        }
                    });
                }
            },
            cancel: {
                btnClass: 'btn-red'
            }
        }
    });
});