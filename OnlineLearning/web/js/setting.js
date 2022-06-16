function deleteSetting(sid, id, type)
{
    var result = confirm("Do you want to delete this setting?");
    if (result)
    {
        window.location.href = "setting-delete?sid=" + sid + "&id=" + id + "&type=" + type;
    }
}
