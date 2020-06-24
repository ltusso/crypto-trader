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

  componentDidMount() {
    fetch("http://localhost:8080/coin")
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          cryptos: x.map((i) => ({
            id: i.id,
            symbol: i.name,
            priceUsd: i.price,
          })),
        })
      );
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
