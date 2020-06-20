import React, {Component} from 'react';
import Card from './components/card_component/CardComponent';

class App extends Component {

  constructor() {
    super();
    this.state = {
      cryptos: [],
    };
  }

  componentDidMount() {
    fetch('https://api.coincap.io/v2/assets')
      .then(response => response.json())
      .then(x => 
        this.setState({ 
          cryptos: x.data.map(i => ({
            id:i.id, 
            symbol:i.symbol,
            priceUsd:i.priceUsd
          })) 
        })); 
  }

  render () {
    
    return (
      this.state.cryptos.map(crypto =>( 
        <Card key={crypto.id} crypto={crypto} />
         ) )
      )
  }
}

export default App;