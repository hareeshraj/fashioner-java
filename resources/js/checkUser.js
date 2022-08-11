const crole = window.location.pathname.split("/").pop();
$.ajax({
  url: "check",
  type: "GET",
  success: function (resp) {
    if (!crole.startsWith(resp)) {
        window.location.replace(resp);
    }
  },
  error: function () {
    window.location.replace("Login");
  },
});

function logout() {
    if (confirm("Are you sure to logout?")) {
      $.ajax({
        url: "logout",
        type: "GET",
      });
      window.location.replace("Login");
    }
  }