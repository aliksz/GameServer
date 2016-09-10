/**
 * Created by alex on 08.09.16.
 */

var LoginForm = React.createClass({
  getInitialState: function() {
    return {username: '', pas: ''};
  },
  handleUsernameChange: function(e) {
    this.setState({username: e.target.value});
  },
  handlePasswordChange: function(e) {
    this.setState({password: e.target.value});
  },
  handleSubmit: function(e) {
    e.preventDefault();
    var username = this.state.username.trim();
    var password = this.state.password.trim();
    if (!password || !username) {
      return;
    }
    this.props.onLoginSubmit({username: username, password: password});
    this.setState({username: '', password: ''});
  },
  render: function() {
    return (
      <form className="LoginForm" onSubmit={this.handleSubmit}>
        <input
          type="text"
          placeholder="Your username"
          value={this.state.username}
          onChange={this.handleUsernameChange}
        />
        <input
          type="text"
          placeholder="Your password"
          value={this.state.password}
          onChange={this.handlePasswordChange}
        />
        <input type="submit" value="Post" />
      </form>
    );
  }
});

var LoginBox = React.createClass({
  
  handleLoginSubmit: function(loginPair) {
    
    fetch(this.props.url, {
      method: 'POST',
      mode: 'no-cors',
      headers: {
        "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
      },
      body: 'username=' + loginPair.username + '&' + 'password=' + loginPair.password
    })
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
  },
  getInitialState: function() {
    return {data: []};
  },

  render: function() {
    return (
      <div className="loginBox">
        <h1>Login</h1>
        <LoginForm onLoginSubmit={this.handleLoginSubmit} />
      </div>
    );
  }
});

ReactDOM.render(
  <LoginBox url="http://localhost:8080/login"/>,
  document.getElementById('content')
);

