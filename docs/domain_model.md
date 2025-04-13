# Entities & Relationships

## Entities
#### Wallet
- ```id``` (UUID)
- ```userId``` (UUID) – User-FK
- ```balance``` (BigDecimal)
- ```createdAt```

**Notes:**
* It is one-to-one with User
* Balance is updated via transactions


#### Transaction
- ```id``` (UUID)
- ```walletId``` (UUID) – Wallet-FK
- ```type``` (enum: DEBIT / CREDIT)
- ```amount``` (BigDecimal)
- ```betId``` (String / UUID) – also used for idempotency
- ```createdAt```

**Notes:**
* Each transaction links to a wallet
* One Debit + One Credit per ```betId``` allowed
* Enforce credit-after-debit logic


## Relationships Overview
User (1) ─── (1) Wallet (1) ─── (*) Transaction


## External Identifiers
- ```userId```
- ```betId```