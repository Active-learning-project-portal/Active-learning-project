// User authentication routing flow
const auth = {
    rootPath: 'auth',
    person: 'user',
    action: {
        signin: 'signin',
        signout: 'signout',
        signup: 'signup',
        forgotPassword: 'forgotPassword'
    }
}

// App routing flow
const app = {
    rootPath: 'app',
    person: 'user',
    action: {
        users: 'users',
        all: 'all',
        inactive: 'inactive',
        active: 'active'
    }
}

export { auth, app }