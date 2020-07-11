import React, { Component } from "react";
import { Table } from "react-bootstrap";

class PurchaseListComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerId: this.props.customerId,
      purchases: [],
      sales: [],
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/purchase/" + this.props.customerId)
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          purchases: x.map((i) => ({
            amount: i.amount,
            price: i.price,
            crypto: {
              name: i.cryptoDTO.name,
              id: i.cryptoDTO.id,
            },
          })),
        })
      );
    fetch("http://localhost:8080/sale/" + this.props.customerId)
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          sales: x.map((i) => ({
            amount: i.amount,
            crypto: {
              name: i.cryptoDTO.name,
              id: i.cryptoDTO.id,
            },
          })),
        })
      );
  }

  render() {
    return (
      <div>
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

        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Coin id</th>
              <th>Amount Sold</th>
            </tr>
          </thead>
          <tbody>
            {this.state.sales.map((sale) => {
              return (
                <tr>
                  <td>{sale.crypto.id}</td>
                  <td>{sale.amount}</td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}
export default PurchaseListComponent;
