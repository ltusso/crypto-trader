import React from 'react'

export const Card = props => (
  <div class="card">
      <div class="card-body">
        <h5 class="card-title">{props.crypto.id}</h5>
        <h6 class="card-subtitle mb-2 text-muted">{props.crypto.priceUsd}</h6>
        <p class="card-text">{props.crypto.symbol}</p>
      </div>
    </div>
);
export default Card;