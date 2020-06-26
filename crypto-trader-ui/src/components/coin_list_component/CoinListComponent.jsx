import React, { Component } from "react";
import Card from "../card_component/CardComponent";
import { Container, Row, Col } from "react-bootstrap";

class CoinListComponent extends Component {
  constructor() {
    super();
    this.state = {
      cryptos: [],
    };
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  componentDidMount() {
    this.loadData();
    //setInterval(this.loadData, 30000);
  }

  loadData() {
    try {
      fetch("http://localhost:8080/coin")
        .then((response) => response.json())
        .then((x) =>
          this.setState({
            cryptos: x.map((i) => ({
              id: i.id,
              symbol: i.name,
              priceUsd: i.price,
              variation: i.variation,
            })),
          })
        );
    } catch (e) {
      console.log(e);
    }
  }

  render() {
    return (
      <Container>
        <Row>
          {this.state.cryptos.map((crypto) => {
            return (
              <Col>
                <Card key={crypto.id} crypto={crypto} />
              </Col>
            );
          })}
        </Row>
      </Container>
    );
  }
}

export default CoinListComponent;
