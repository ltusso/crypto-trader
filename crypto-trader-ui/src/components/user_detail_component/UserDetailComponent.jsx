import React, { Component } from "react";

class UserDetailComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerId: this.props.customerId,
    };
  }

  render() {
    return <div> My operations are shown here.</div>;
  }
}
export default UserDetailComponent;
