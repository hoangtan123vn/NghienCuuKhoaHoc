
class LoginRequest {
  late String _username;
  late String _password;


  String get username => _username;
  String get password => _password;

  LoginRequest({
     required String username,
     required String password,
  }){
    _username = username;
    _password = password;

  }

  LoginRequest.fromJson(dynamic json) {
    _username = json["username"];
    _password = json["password"];

  }

  Map<String, dynamic> toJson() {
    var map = <String, dynamic>{};
    map["username"] = _username;
    map["password"] = _password;
    return map;
  }
}