import React, { Component } from "react";
import { Button } from "react-bootstrap";
import AmountConfirmation from "./../amount_confirmation_component/AmountConfirmationComponent";

class BuyButton extends Component {
  constructor(props) {
    super(props);
    this.state = {
      crypto: this.props.crypto,
      buy: false,
    };
  }

  purchaseCrypto = (id, name, price, amount) => {
    console.info("purchased!!!");
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        cryptoDTO: {
          name: name,
          id: id,
        },
        priceUsd: price,
        amountToBuy: amount,
        customerId: 1,
      }),
    };
    fetch("http://localhost:8080/purchase", requestOptions);
    this.setState({ buy: false });
  };

  enablePurchase = (event) => {
    this.setState({ buy: true });
  };

  render() {
    return this.state.buy ? (
      <AmountConfirmation
        onPurchase={this.purchaseCrypto}
        crypto={this.state.crypto}
      />
    ) : (
      <Button onClick={this.enablePurchase}>Buy</Button>
    );
  }
}

export default BuyButton;
