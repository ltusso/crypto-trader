import React, { Component } from "react";
import CoinListComponent from "./components/coin_list_component/CoinListComponent";

class App extends Component {
  constructor() {
    super();
    this.state = {
      cryptos: [],
    };
  }

  render() {
    return <CoinListComponent />;
  }
}

export default App;
