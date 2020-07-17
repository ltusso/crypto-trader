import React from "react";
import { Button } from "react-bootstrap";

export const AmountConfirmation = (props) => {
  if (props.type === "sale") {
    return (
      <div>
        <div>
          <input
            placeholder="Enter amount"
            id="purchasedAmount"
            onChange={props.onAmountSell}
          />
        </div>
        <div>
          <Button onClick={() => props.onSale(props.code)}>Sell</Button>
          &nbsp;
          <Button onClick={props.onCancelSale}>Cancel</Button>
        </div>
      </div>
    );
  } else {
    return (
      <div>
        <div>
          <input
            placeholder="Enter amount"
            id="purchasedAmount"
            onChange={props.onAmountPurchased}
          />
        </div>
        <div>
          <Button
            onClick={() =>
              props.onPurchase(
                props.crypto.id,
                props.crypto.symbol,
                props.crypto.priceUsd
              )
            }
          >
            Confirm
          </Button>
          &nbsp;
          <Button onClick={props.onCancelPurchase}>Cancel</Button>
        </div>
      </div>
    );
  }
};
export default AmountConfirmation;
