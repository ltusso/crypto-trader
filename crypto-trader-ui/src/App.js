import React, { Component } from "react";
import Card from "./components/card_component/CardComponent";

import { Container, Row, Col } from "react-bootstrap";

class App extends Component {
  constructor() {
    super();
    this.state = {
      cryptos: [],
    };
  }

  componentDidMount() {
    fetch("https://api.coincap.io/v2/assets")
      .then((response) => response.json())
      .then((x) =>
        this.setState({
          cryptos: x.data.map((i) => ({
            id: i.id,
            symbol: i.symbol,
            priceUsd: i.priceUsd,
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

export default App;
