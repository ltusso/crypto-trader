import React, { Component } from "react";
import { Table } from "react-bootstrap";
class UserDetailComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerId: this.props.customerId,
      purchases: [],
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/customer/" + this.props.customerId)
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          purchases: x.purchases.map((i) => ({
            amount: i.amount,
            price: i.totalPrice,
            crypto: {
              name: i.cryptoDTO.name,
              id: i.cryptoDTO.id,
            },
          })),
        })
      );
  }

  render() {
    /*  return this.state.purchases.map((purchase) => {
      return <span>{purchase.crypto.id}</span>;   
    });
    */

    return (
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Coin id</th>
            <th>Amount Purchased</th>
            <th>Total Price</th>
          </tr>
        </thead>
        <tbody>
          {this.state.purchases.map((purchase) => {
            return (
              <tr>
                <td>{purchase.crypto.id}</td>
                <td>{purchase.amount}</td>
                <td>{purchase.price}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    );
  }
}
export default UserDetailComponent;
