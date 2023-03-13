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
const alp = {
    rootPath: 'alp',
    action: {
        users: 'users',
    }
}

export { auth }