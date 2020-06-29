import React, { Component } from "react";
import { Button } from "react-bootstrap";
import AmountConfirmation from "./../amount_confirmation_component/AmountConfirmationComponent";

class BuyButton extends Component {
  constructor(props) {
    super(props);
    this.state = {
      crypto: this.props.crypto,
      buy: false,
      purchasedAmount: "",
    };
  }

  handlePurchasedAmountChange = (event) => {
    console.info(event.target.value);
    this.setState({ purchasedAmount: event.target.value });
  };

  purchaseCrypto = (id, name, price) => {
    console.info("purchased!!!");
    const { purchasedAmount } = this.state;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        cryptoDTO: {
          name: name,
          id: id,
        },
        price: price,
        amount: purchasedAmount,
        customerId: 1,
      }),
    };
    fetch("http://localhost:8080/purchase", requestOptions);
    this.setState({ buy: false });
  };

  enablePurchase = (event) => {
    this.setState({ buy: true });
  };

  cancelPurchase = (event) => {
    this.setState({ buy: false });
  };

  render() {
    return this.state.buy ? (
      <div Style="width:100%">
        <AmountConfirmation
          onPurchase={this.purchaseCrypto}
          crypto={this.state.crypto}
          onAmountPurchased={this.handlePurchasedAmountChange}
          onCancelPurchase={this.cancelPurchase}
        />
      </div>
    ) : (
      <Button onClick={this.enablePurchase}>Buy</Button>
    );
  }
}

export default BuyButton;
