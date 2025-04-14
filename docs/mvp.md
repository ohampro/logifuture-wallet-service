
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

**API Naming Justification**
- Should it be "/users/{id}" or "/wallet/{userId}"?
   1. According to the [ProblemStatement.md](/docs/ProblemStatement.md), the statmens show a one-to-one relationship between the user and the wallet. 
   2. The documant does not mention retrieving a list of user's wallets.
   3. The documant does not clarify whether a user can select a wallet; it instead refers to "user balance," which suggests a single wallet per user.
   4. When the "/wallet/{userId}" points to the only wallet a user has, and the API consumer does not have a walletId, "/users/{id}" seems more appropriate and concise than "/wallet/{userId}".
   


## Out of Scope
These features won’t be implemented:
- User registration or authentication
- Multi-currency or internationalization
- External payment gateway integration
- Admin dashboards or analytics
- High-availability cluster deployment
- Distributed transaction handling (eventual consistency)