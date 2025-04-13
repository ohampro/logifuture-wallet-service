
## Scope
### Main Features / Requirements

#### Wallet Balance
- Check current balance for a user
- Maintain a consistent, up-to-date balance

#### Transactions
- **Debit**: Subtract bet amount from wallet
- **Credit**: Add winning amount after a debit
- Ensure one debit and one credit per bet (e.g., via betId)
- Enforce transactional integrity: no credit without debit

#### Transaction History
- List of transactions filtered by user ID
- Include type (DEBIT, CREDIT) and timestamp

#### Concurrency & Idempotency
- Handle high-concurrency with thread-safe operations
- Prevent duplicate processing using unique bet IDs

#### Fault Tolerance
- Resilient DB operations (e.g., retry logic if needed)
- Proper exception handling and safe rollback on failure

#### RESTful API
Expose endpoints for all required operations
- GET   /users/{id}/balance
- GET   /users/{id}/transactions
- POST  /transactions/debit
- POST  /transactions/credit


## Out of Scope
These features won’t be implemented:
- User registration or authentication
- Multi-currency or internationalization
- External payment gateway integration
- Admin dashboards or analytics
- High-availability cluster deployment
- Distributed transaction handling (eventual consistency)