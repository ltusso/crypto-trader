import React, { Component } from "react";
import { Button } from "react-bootstrap";
import AmountConfirmation from "../amount_confirmation_component/AmountConfirmationComponent";

class TradeButton extends Component {
  constructor(props) {
    super(props);
    this.state = {
      crypto: this.props.crypto,
      type: this.props.type,
      code: this.props.code,
      buy: false,
      sale: false,
      purchasedAmount: "",
      sellAmount: "",
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
        amount: purchasedAmount,
        customerId: 1,
      }),
    };
    fetch("http://localhost:8080/purchase", requestOptions)
      .then(function (response) {
        if (response.ok) {
          console.log("done");
          window.location.reload(false);
        }
        throw new Error("Something went wrong.");
      })
      .catch(function (error) {
        console.log("error");
      });
  };

  enablePurchase = (event) => {
    this.setState({ buy: true });
  };

  cancelPurchase = (event) => {
    this.setState({ buy: false });
  };

  handleSellAmountChange = (event) => {
    console.info(event.target.value);
    this.setState({ sellAmount: event.target.value });
  };

  sellCrypto = (id) => {
    const { sellAmount } = this.state;
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        cryptoDTO: {
          name: id,
          id: id,
        },
        amount: sellAmount,
        customerId: 1,
      }),
    };
    fetch("http://localhost:8080/sale", requestOptions)
      .then(function (response) {
        if (response.ok) {
          window.location.reload(false);
        }
        throw new Error("Something went wrong.");
      })
      .catch(function (error) {
        console.log("error");
      });
  };

  enableSale = (event) => {
    this.setState({ sell: true });
  };

  cancelSale = (event) => {
    this.setState({ sell: false });
  };

  render() {
    if (this.state.type == "sell") {
      return this.state.sell ? (
        <div Style="width:100%">
          <AmountConfirmation
            type="sale"
            onSale={this.sellCrypto}
            code={this.state.code}
            onAmountSell={this.handleSellAmountChange}
            onCancelSale={this.cancelSale}
          />
        </div>
      ) : (
        <Button onClick={this.enableSale}>Sell</Button>
      );
    } else {
      return this.state.buy ? (
        <div Style="width:100%">
          <AmountConfirmation
            type="buy"
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
}

export default TradeButton;
