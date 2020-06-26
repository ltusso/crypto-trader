import React, { Component } from "react";
import { Table } from "react-bootstrap";

class CustomerInfoComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerId: this.props.customerId,
    };
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  componentDidMount() {
    this.loadData();
    setInterval(this.loadData, 30000);
  }

  async loadData() {
    try {
      fetch("http://localhost:8080/customer/" + this.props.customerId)
        .then((response) => response.json())
        .then((x) =>
          this.setState({
            name: x.name,
            budget: x.budget,
          })
        );
    } catch (e) {
      console.log(e);
    }
  }

  render() {
    return (
      <div>
        <h3> Hey {this.state.name}</h3>{" "}
        <p> Your budget is {this.state.budget}</p>
      </div>
    );
  }
}
export default CustomerInfoComponent;
