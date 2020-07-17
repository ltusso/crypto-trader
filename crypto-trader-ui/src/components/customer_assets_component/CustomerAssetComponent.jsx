import React, { Component } from "react";
import { Table } from "react-bootstrap";
import TradeButton from "../trade_button_component/TradeButtonComponent";

class CustomerAssetComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      customerId: this.props.customerId,
      assets: [],
    };
  }

  componentDidMount() {
    fetch("http://localhost:8080/customer/" + this.props.customerId)
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          assets: x.assets.map((i) => ({
            amount: i.amount,
            code: i.code,
          })),
        })
      );
  }

  render() {
    return (
      <div>
        <h1>Assets </h1>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Coin</th>
              <th>Amount</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {this.state.assets.map((asset) => {
              return (
                <tr>
                  <td>{asset.code}</td>
                  <td>{asset.amount}</td>
                  <td>
                    {" "}
                    <TradeButton key={asset.code} code={asset.code} type="sell">
                      Sell
                    </TradeButton>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}
export default CustomerAssetComponent;
