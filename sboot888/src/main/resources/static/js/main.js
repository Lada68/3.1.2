

$(document).ready(function() {

    $('.table #editButton').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $(' #id1').val(user.id);
            $('#name1').val(user.name);
            $('#surname1').val(user.surname);
            $('#age1').val(user.age);
            $('#username1').val(user.username);
            $('#password1').val(user.password);
            $(' #roles1').val(getRoles(user));
        });
        $('#editModal').modal();
    });
    $(".table #deleteButton").on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $(' #id2').val(user.id);
            $('#name2').val(user.name);
            $('#surname2').val(user.surname);
            $('#age2').val(user.age);
            $('#username2').val(user.username);
            $('#roles2').val(getRoles(user));
        });

        $('#deleteModal').modal();
    });
    function getRoles(user){
        let roles = "";
        let i = user.roles.length - 1;
        while (i !== -1){
            roles += user.roles[i].name;    roles += " ";    i--    }    return roles;       }

});
